/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		// Invoke BeanDefinitionRegistryPostProcessors first, if any.
		//优先执行BeanDefinitionRegistryPostProcessors
		//将已经执行的BFPP存储在processedBeans中，防止重复执行
		Set<String> processedBeans = new HashSet<>();

	/*	判断BeanFactory是否是BeanDefinitionRegistry类型，此处是DefaultListableBeanFactory，实现了BeanDefinitionRegistry接口
	* 		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
	* 		方法中返回的beanFactory是BeanDefinitionRegistry类型，ConfigurableListableBeanFactory只是接口类
	* */
		if (beanFactory instanceof BeanDefinitionRegistry) {
			//强制类型转换
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
			/*
			* BeanFactoryPostProcessor主要针对的操作对象是BeanFactory,
			* BeanDefinitionRegistryPostProcessor主要针对BeanDefinition
			* */
			//存放BeanFactoryPostProcessor集合
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
			//存放BeanDefinitionRegistryPostProcessor集合
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

			/*
			* 首先处理BeanFactoryPostProcessor，遍历所有的BeanFactoryPostProcessors
			* 将 BeanDefinitionRegistryPostProcessor 和BeanFactoryPostProcessor  区分开
			* */
			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					/*直接执行BeanDefinitionRegistryPostProcessor接口中的postProcessBeanDefinitionRegistry方法
						在此过程中可能会添加新的postProcessBeanFactory，故后面会有重复代码对相关新增的进行处理
					 *
					 * */
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					registryProcessors.add(registryProcessor);
				}
				else {
					//普通的BeanFactoryPostProcessor，添加到相关集合，在后续集中执行postProcessBeanFactory方法
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			//用于保存本次要执行的BeanDefinitionRegistryPostProcessor
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			/*	调用所有实现PriorityOrdered接口的BeanDefinitionRegistryPostProcessor实现类
			找到所有实现BeanDefinitionRegistryPostProcessor 接口的bean的beanName*/
			String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			//遍历处理所有的符合规则的postProcessorNames
			for (String ppName : postProcessorNames) {
				//检测是否实现PriorityOrdered接口
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					processedBeans.add(ppName);
				}
			}
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			//添加registryProcessors，用于最后执行的postProcess方法
			registryProcessors.addAll(currentRegistryProcessors);
			//遍历currentRegistryProcessors，执行postProcessBeanDefinitionRegistry
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			//执行完毕，清空currentRegistryProcessors集合
			currentRegistryProcessors.clear();

			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			/*调用所有实现PriorityOrdered接口的BeanDefinitionRegistryPostProcessor实现类
			找到所有实现BeanDefinitionRegistryPostProcessor 接口的bean的beanName
			 此处需要重复查找的原因在于可能在上面执行的过程中会新增新的BeanDefinitionRegistryPostProcessor*/
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				//检测是否实现Ordered接口，并且还未被执行过
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
				/*	获取名字对应的bean实例，添加到currentRegistryProcessors中
				* beanFactory.getBean中会进行相关相关bean的实例化工作
				* */
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					//将要执行的BFPP名称添加到processedBeans中，避免后续重复执行
					processedBeans.add(ppName);
				}
			}
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			//添加registryProcessors，用于最后执行的postProcess方法
			registryProcessors.addAll(currentRegistryProcessors);
			//遍历currentRegistryProcessors，执行postProcessBeanDefinitionRegistry
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			//执行完毕，清空currentRegistryProcessors集合
			currentRegistryProcessors.clear();

			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			boolean reiterate = true;
			while (reiterate) {
				reiterate = false;
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				for (String ppName : postProcessorNames) {
					if (!processedBeans.contains(ppName)) {
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						processedBeans.add(ppName);
						reiterate = true;
					}
				}
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				registryProcessors.addAll(currentRegistryProcessors);
				//在此调用自定义的selfBeanPostRegistryProcessor
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				currentRegistryProcessors.clear();
			}

			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
			//调用所有的BeanDefinitionRegistryPostProcessor的postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
			//最后调用父类的入参BeanFactoryPostProcessor中普通BeanFactoryPostProcessor的postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		}

		else {
			// Invoke factory processors registered with the context instance.
			//如果beanFactory不属于BeanDefinitionRegistry类型，直接执行postProcessBeanFactory方法
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		/*
		 *	到此为止，入参beanFactoryProcessors和容器中所有的BeanDefinitionRegistryPostProcessor已经全部处理完成
		 * 现在开始处理BeanFactoryPostProcessor
		 * 这里可能存在一部分只实现了BeanFactoryPostProcessor,
		 * 没有实现BeanDefinitionRegistryPostProcessor接口的类
		 */
		/*
		* 此处代码beanFactory.getBeanNamesForType在上面进行多次调用
		* 但是传递参数不同，BeanDefinitionRegistryPostProcessor.class会注册实现BeanFactoryPostProcessor接口的类
		* 但是BeanFactoryPostProcessor不会注册新的实现BeanFactoryPostProcessor接口的类
		* */
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		//用于存放实现了priorityOrdered接口的beanName集合
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		//用于存放实现了ordered接口的BeanFactoryProcessor的beanName
		List<String> orderedPostProcessorNames = new ArrayList<>();
		//存放普通的BeanFactoryProcessor的beanName
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		//遍历postProcessorNames，将三种不同集合区分开
		for (String ppName : postProcessorNames) {
			if (processedBeans.contains(ppName)) {
				//已经执行过的BeanFactoryPostProcessor不再执行
				// skip - already processed in first phase above
			}
			else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			}
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			}
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		//对实现priorityOrdered接口的BeanFactoryPostProcessor进行排序
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		//对实现priorityOrdered接口的BeanFactoryPostProcessor执行PostProcessorBeanFactory方法
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String postProcessorName : orderedPostProcessorNames) {
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		//对实现ordered接口的BeanFactoryPostProcessor进行排序操作
		sortPostProcessors(orderedPostProcessors, beanFactory);
		//遍历实现了ordered接口的BeanFactoryPostProcessor，执行PostProcessorBeanFactory方法
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		//创建存放BeanFactoryPostProcessor的集合对象
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		//遍历nonOrderedPostProcessors, 执行postProcessBeanFactory方法
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);

		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		//清除元数据缓存（mergedBeanDefinitions、allBeanNamesByType、singletonBeanNamesByType），
		//因为后处理器可能已经修改了原始元数据，例如， 替换值中的占位符...
		beanFactory.clearMetadataCache();
	}

	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {
		// 找到所有实现了BeanPostProcessor接口的类
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		// 记录下BeanPostProcessor的目标计数
		// 此处为什么要+1呢，原因非常简单，在此方法的最后会添加一个BeanPostProcessorChecker的类(保证BeanPostProcessorChecker为第一个)
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		// 添加BeanPostProcessorChecker(主要用于记录信息)到beanFactory中
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 定义存放实现了PriorityOrdered接口的BeanPostProcessor集合
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		// 定义存放spring内部的BeanPostProcessor
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		// 定义存放实现了Ordered接口的BeanPostProcessor的name集合
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 遍历beanFactory中存在的BeanPostProcessor的集合postProcessorNames，
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		for (String ppName : postProcessorNames) {
			// 如果ppName对应的BeanPostProcessor实例实现了PriorityOrdered接口，则获取到ppName对应的BeanPostProcessor的实例添加到priorityOrderedPostProcessors中
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				priorityOrderedPostProcessors.add(pp);
				// 如果ppName对应的BeanPostProcessor实例也实现了MergedBeanDefinitionPostProcessor接口，那么则将ppName对应的bean实例添加到internalPostProcessors中
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					internalPostProcessors.add(pp);
				}
			}
			// 如果ppName对应的BeanPostProcessor实例没有实现PriorityOrdered接口，但是实现了Ordered接口，那么将ppName对应的bean实例添加到orderedPostProcessorNames中
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				// 否则将ppName添加到nonOrderedPostProcessorNames中
				orderedPostProcessorNames.add(ppName);
			}
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, register the BeanPostProcessors that implement PriorityOrdered.
		// 首先，对实现了PriorityOrdered接口的BeanPostProcessor实例进行排序操作
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		// 注册实现了PriorityOrdered接口的BeanPostProcessor实例添加到beanFactory中
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		// Next, register the BeanPostProcessors that implement Ordered.
		// 注册所有实现Ordered的beanPostProcessor
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String ppName : orderedPostProcessorNames) {
			// 根据ppName找到对应的BeanPostProcessor实例对象
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			// 将实现了Ordered接口的BeanPostProcessor添加到orderedPostProcessors集合中
			orderedPostProcessors.add(pp);
			// 如果ppName对应的BeanPostProcessor实例也实现了MergedBeanDefinitionPostProcessor接口，那么则将ppName对应的bean实例添加到internalPostProcessors中
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		// 对实现了Ordered接口的BeanPostProcessor进行排序操作
		sortPostProcessors(orderedPostProcessors, beanFactory);
		//  注册实现了Ordered接口的BeanPostProcessor实例添加到beanFactory中
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		// 创建存放没有实现PriorityOrdered和Ordered接口的BeanPostProcessor的集合
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		// 遍历集合
		for (String ppName : nonOrderedPostProcessorNames) {
			// 根据ppName找到对应的BeanPostProcessor实例对象
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			// 将没有实现PriorityOrdered和Ordered接口的BeanPostProcessor添加到nonOrderedPostProcessors集合中
			nonOrderedPostProcessors.add(pp);
			// 如果ppName对应的BeanPostProcessor实例也实现了MergedBeanDefinitionPostProcessor接口，那么则将ppName对应的bean实例添加到internalPostProcessors中
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		//  注册没有实现PriorityOrdered和Ordered的BeanPostProcessor实例添加到beanFactory中
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		// 将所有实现了MergedBeanDefinitionPostProcessor类型的BeanPostProcessor进行排序操作
		sortPostProcessors(internalPostProcessors, beanFactory);
		// 注册所有实现了MergedBeanDefinitionPostProcessor类型的BeanPostProcessor到beanFactory中
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		// 注册ApplicationListenerDetector到beanFactory中
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	/**
	 * 对 postProcessors 进行排序，优先使用beanFactory的DependencyComparator，获取不到就使用OrderComparator.INSTANCE
	 * @param postProcessors
	 * @param beanFactory
	 */
	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		// Nothing to sort?
		if (postProcessors.size() <= 1) {
			return;
		}
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			//获取比较器
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			//模版方法
			//回调 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法，使得每个postProcessor对象都可以对
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			//将 postProcessor 添加到beanFactory,它将应用于该工厂创建的Bean。在工厂配置期间调用
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
