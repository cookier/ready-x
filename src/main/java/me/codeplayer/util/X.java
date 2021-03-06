package me.codeplayer.util;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;

import javax.annotation.*;

import org.slf4j.*;

/**
 * 通用公共工具类<br>
 * 此类全为静态方法，请使用静态方法的形式调用<br>
 * 由于均为静态方法，所以类名尽可能地短，以免干扰逻辑可读性<br>
 * 并且工具类会经常被调用，类名短，也方便开发人员编写。
 * 
 * @author Ready
 */
public abstract class X {

	/**
	 * 获取调用此方法的当前类的日志处理器(Logger)<br>
	 * 该Logger使用slf4j API
	 * 
	 * @return
	 */
	public static final Logger getLogger() {
		String className = new Throwable().getStackTrace()[1].getClassName();
		return LoggerFactory.getLogger(className);
	}

	/**
	 * 判断指定的字符串是否为空<br>
	 * 如果字符串为null、空字符串,则返回true<br>
	 * <b>注意：</b>本方法不会去除字符串两边的空格，如果想去除字符串两边的空格后再进行判断，可以使用isBlank()方法
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean isEmpty(String str) {
		return StringUtil.isEmpty(str); // 后面的表达式相当于"".equals(str)，但比其性能稍好
	}

	/**
	 * 判断指定的对象是否为空<br>
	 * 如果对象(或其toSring()返回值)为null、空字符串，则返回true<br>
	 * <b>注意：</b>本方法不会去除字符串两边的空格，如果想去除字符串两边的空格后再进行判断，可以使用isBlank()方法
	 * 
	 * @param obj 指定的对象
	 * @return
	 */
	public static final boolean isEmpty(Object obj) {
		return StringUtil.isEmpty(obj); // 后面的表达式相当于"".equals(str)，但比其性能稍好
	}

	/**
	 * 判断指定的字符串是否为null或去空格后为空字符串，如果是则返回true
	 * 
	 * @param str 指定的字符串对象
	 * @return
	 */
	public static final boolean isBlank(String str) {
		return StringUtil.isBlank(str);
	}

	/**
	 * 判断指定的对象是否为空<br>
	 * 如果对象(或其toSring()返回值)为null、空字符串、空格字符串，则返回true<br>
	 * <b>注意：</b>本方法会先去除字符串两边的空格，再判断
	 * 
	 * @param obj 指定的对象
	 * @return
	 * @see me.codeplayer.util.StringUtil#isBlank(Object)
	 */
	public static final boolean isBlank(Object obj) {
		return StringUtil.isBlank(obj);
	}

	/**
	 * 从指定的多个值依次检测并选取第一个不为null的值
	 */
	public static final <T> T expectNotNull(T v1, T v2, T v3, T v4) {
		if (v1 != null) {
			return v1;
		} else if (v2 != null) {
			return v2;
		} else if (v3 != null) {
			return v3;
		} else if (v4 != null) {
			return v4;
		}
		return null;
	}

	/**
	 * 从指定的多个值依次检测并选取第一个不为null的值
	 * 
	 * @see X#expectNotNull(Object, Object, Object, Object)
	 */
	public static final <T> T expectNotNull(T v1, T v2, T v3) {
		return expectNotNull(v1, v2, v3, null);
	}

	/**
	 * 从指定的多个值依次检测并选取第一个不为null的值
	 * 
	 * @see #expectNotNull(Object, Object, Object, Object)
	 */
	public static final <T> T expectNotNull(T v1, T v2) {
		return expectNotNull(v1, v2, null, null);
	}

	/**
	 * 从指定的多个字符串依次检测并选取第一个不为空字符串的值，否则返回空字符串""
	 */
	public static final String expectNotEmpty(String v1, String v2, String v3, String v4) {
		if (v1 != null && v1.length() > 0) {
			return v1;
		} else if (v2 != null && v2.length() > 0) {
			return v2;
		} else if (v3 != null && v3.length() > 0) {
			return v3;
		} else if (v4 != null && v4.length() > 0) {
			return v4;
		}
		return "";
	}

	/**
	 * 从指定的多个字符串依次检测并选取第一个不为空字符串的值，否则返回空字符串""
	 */
	public static final String expectNotEmpty(String v1, String v2, String v3) {
		return expectNotEmpty(v1, v2, v3, null);
	}

	/**
	 * 从指定的多个字符串依次检测并选取第一个不为空字符串的值，否则返回空字符串""
	 */
	public static final String expectNotEmpty(String v1, String v2) {
		return expectNotEmpty(v1, v2, null, null);
	}

	/**
	 * 判断指定Boolean值是否有效。<code>true</code> 即为有效。
	 * 
	 * @param b 指定的Boolean对象
	 * @return
	 */
	public static final boolean isValid(Boolean b) {
		return b != null && b;
	}

	/**
	 * 判断指定的数值对象是否有效。如果参数为 <code>null</code> 或 数值等于0，则为无效；其他均为有效。
	 * 
	 * @param number 指定的
	 * @return
	 */
	public static final boolean isValid(Number number) {
		return number != null && number.doubleValue() != 0;
	}

	/**
	 * 判断指定的字符串序列是否有效。如果参数为 <code>null</code> 或空字符串 <code>""</code> ，则为无效；其他均为有效。
	 * 
	 * @param sequence 指定的字符串序列对象
	 * @return
	 */
	public static final boolean isValid(CharSequence sequence) {
		return StringUtil.notEmpty(sequence);
	}

	/**
	 * 判断指定的Map对象是否有效。如果参数为 <code>null</code> 或 <code>map.size() == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param map 指定的映射集合对象
	 * @return
	 */
	public static final boolean isValid(Map<?, ?> map) {
		return map != null && map.size() > 0;
	}

	/**
	 * 判断指定的Collection对象是否有效。如果参数为 <code>null</code> 或 <code>collection.size() == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param collection 指定的集合对象
	 * @return
	 */
	public static final boolean isValid(Collection<?> collection) {
		return collection != null && collection.size() > 0;
	}

	/**
	 * 判断指定byte数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param array 指定的byte数组
	 * @return
	 */
	public static final boolean isValid(byte[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定int数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param array 指定的int数组
	 * @return
	 */
	public static final boolean isValid(int[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定long数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param array 指定的long数组
	 * @return
	 */
	public static final boolean isValid(long[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定char数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param array 指定的char数组
	 * @return
	 */
	public static final boolean isValid(char[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定float数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param array 指定的float数组
	 * @return
	 */
	public static final boolean isValid(float[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定double数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param array 指定的double数组
	 * @return
	 */
	public static final boolean isValid(double[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定的对象数组是否有效。如果参数为 <code>null</code> 或 <code>array.length == 0</code>，则为无效，其他均为有效。
	 * 
	 * @param collection 指定的对象数组
	 * @return
	 */
	public static final boolean isValid(Object[] array) {
		return array != null && array.length > 0;
	}

	/**
	 * 判断指定对象是否无效。只有以下情况视为无效，其他均为有效。<br>
	 * 无效的参数对象arg的定义如下(按照判断顺序排序)： <br>
	 * 1.<code>arg == null</code><br>
	 * 2.如果<code>arg</code>是字符序列对象，去空格后，<code>arg.length() == 0</code><br>
	 * 3.如果<code>arg</code>是数值对象，去空格后，<code>值  == 0</code><br>
	 * 4.如果<code>arg</code>是映射集合(Map)对象，<code>arg.size() == 0</code><br>
	 * 5.如果<code>arg</code>是集合(Collection)对象，<code>arg.size() == 0</code><br>
	 * 6.如果<code>arg</code>是数组(Array)对象，<code>arg.length == 0</code><br>
	 * 7.如果<code>arg</code>是布尔(Boolean)对象，<code>arg 等价于  false</code><br>
	 * 
	 * @param arg
	 * @return 上述无效的情况返回 <code>false</code>，其他情况均返回 <code>true</code>
	 */
	@SuppressWarnings("rawtypes")
	public static final boolean isValid(Object arg) {
		if (arg == null) {
			return false;
		} else if (arg instanceof CharSequence) {
			return ((CharSequence) arg).length() > 0;
		} else if (arg instanceof Number) {
			return ((Number) arg).doubleValue() != 0;
		} else if (arg instanceof Map) {
			return ((Map) arg).size() > 0;
		} else if (arg instanceof Collection) {
			return ((Collection) arg).size() > 0;
		} else if (arg.getClass().isArray()) {
			return Array.getLength(arg) > 0;
		} else if (arg instanceof Boolean) {
			return (Boolean) arg;
		} else {
			return true;
		}
	}

	/**
	 * 将指定的值根据指定的表达式解析，并返回解析后的结果
	 * 
	 * @param value 指定的值
	 * @param expressions 指定的表达式，例如：<code>("1", "男", "0", "女")</code>方法将会将指定属性的值(value)，与表达式进行匹配，形如：
	 * 
	 *            <pre>
	 * <code> 
	 * if(value 等于 "1"){
	 * 	return "男";
	 * }else if(value 等于 "0"){
	 * 	return "女";
	 * }else{
	 * 	return value;
	 * }
	 * </code>
	 *            </pre>
	 * 
	 *            本方法接收的表达式参数个数可以为奇数，例如：<code>(6, "星期六", 7, "星期天", "工作日")</code>，相当于：
	 * 
	 *            <pre>
	 * if(value 等于 6){
	 * 	return "星期六";
	 * }else if(value 等于 7){
	 * 	return "星期天";
	 * }else{
	 * 	return "工作日";
	 * }
	 *            </pre>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T decode(T value, T... expressions) {
		int length;
		if (expressions == null || (length = expressions.length) == 0) {
			throw new IllegalArgumentException("decode的表达式参数个数不能小于1!");
		}
		int i = 0;
		if ((length & 1) == 1) {// 如果是奇数
			int index = length - 1;
			while (i < index) {
				if (value == expressions[i] || value != null && value.equals(expressions[i])) {
					break;
				}
				i += 2;
			}
			return i < index ? expressions[i + 1] : expressions[index];
		} else {// 如果是偶数
			do {
				if (value == expressions[i] || value != null && value.equals(expressions[i])) {
					break;
				}
			} while ((i += 2) < length);
			return i < length ? expressions[i + 1] : value;
		}
	}

	/**
	 * 去除字符串两端的空格<br>
	 * 如果字符串为null、空字符串""、空白字符串，这返回HTML的空格符"&amp;nbsp;"
	 * 
	 * @param str
	 * @return
	 */
	public static final String trim4Html(String str) {
		return StringUtil.isBlank(str) ? "&nbsp;" : str.trim();
	}

	/**
	 * 根据需要存储的元素个数确定HashMap等Map接口实现类的初始容量(使用默认的负载因子：0.75)
	 * 
	 * @param capacity 需要存储的元素个数
	 * @return
	 */
	public static final int getCapacity(int capacity) {
		return getCapacity(capacity, 0.75f);
	}

	/**
	 * 根据需要存储的元素个数确定HashMap等Map接口实现类的初始容量
	 * 
	 * @param capacity 需要存储的元素个数
	 * @param loadFactor 负载因子，必须介于0-1之间，如果不在此范围，内部也不检测，后果自负
	 * @return
	 */
	public static final int getCapacity(int capacity, float loadFactor) {
		int initCapacity = 16;
		while (capacity > initCapacity * loadFactor) {
			initCapacity <<= 1;// 如果默认容量小于指定的期望，则扩大一倍
		}
		return initCapacity;
	}

	/**
	 * 将指定泛型对象进行泛型擦除，并转换为对应的泛型声明
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T castType(Object obj) {
		return (T) obj;
	}

	/**
	 * 对指定的对象执行执行的 {@code mapper } 转换，安全地获得期望的转换结果
	 * 
	 * @param obj 指定的对象，可以为 null
	 * @param mapper 转换器
	 * @return 如果 {@code obj == null } 则返回null，否则返回转换后的结果
	 * @author Ready
	 * @since 2.3.0
	 */
	public static final <T, R> R map(@Nullable T obj, Function<? super T, R> mapper) {
		if (obj == null) {
			return null;
		}
		return mapper.apply(obj);
	}

	/**
	 * 尝试拆箱可能由 {@link Supplier } 接口包装的实体对象
	 * 
	 * @param supplier
	 * @return 如果指定参数实现了 {@link Supplier } 接口，则调用 get() 方法 并返回其值；否则直接返回 该对象本身
	 * @author Ready
	 * @since 2.3.0
	 */
	public static final Object tryUnwrap(Object supplier) {
		if (supplier instanceof Supplier) {
			return ((Supplier<?>) supplier).get();
		}
		return supplier;
	}

	/**
	 * 检测指定的对象在经过指定的转换后，是否符合指定的条件
	 * 
	 * @param bean 指定的对象
	 * @param mapper 转换器
	 * @param matcher 条件判断器
	 * @return
	 * @author Ready
	 * @since 2.3.0
	 */
	public static final <T, R> boolean isMatch(@Nullable T bean, final Function<? super T, R> mapper, final Predicate<? super R> matcher) {
		final R val = map(bean, mapper);
		return matcher.test(val);
	}

	/**
	 * 将指定的异常信息封装为运行时异常
	 * 
	 * @param forceUseMsg 如果指定的异常是运行时异常，且 {@code msg } 不为null；此时是否还需要包装一个 {@link IllegalArgumentException } 来确保强制使用传入的 {@code msg } 作为异常信息
	 * @return 如果异常 {@code ex } 为 null，或者不是运行时异常，则自动将其封装为 {@link IllegalArgumentException }；否则返回对应的运行时异常
	 * @since 2.3.0
	 */
	public static final RuntimeException wrapException(final @Nullable String msg, final boolean forceUseMsg, final @Nullable Throwable ex, final @Nullable Throwable cause) {
		if (ex == null) {
			return cause == null ? new IllegalArgumentException(msg) : new IllegalArgumentException(msg, cause);
		} else if (ex instanceof RuntimeException) {
			return forceUseMsg && msg != null ? new IllegalArgumentException(msg, ex) : (RuntimeException) ex;
		} else {
			return msg == null ? new IllegalArgumentException(ex) : new IllegalArgumentException(msg, ex);
		}
	}

	/**
	 * 将指定的异常信息封装为运行时异常
	 * <p>
	 * 注意：如果指定的异常是运行时异常；此时不会使用传入的 {@code msg }
	 * 
	 * @return 如果异常 {@code ex } 为 null，或者不是运行时异常，则自动将其封装为 {@link IllegalArgumentException }；否则返回对应的运行时异常
	 * @since 2.3.0
	 */
	public static final RuntimeException wrapException(final @Nullable String msg, final @Nullable Throwable ex, final @Nullable Throwable cause) {
		return wrapException(msg, false, ex, cause);
	}

	/**
	 * 将指定的异常信息封装为运行时异常
	 * 
	 * @param forceUseMsg 如果指定的异常是运行时异常，且 {@code msg } 不为null；此时是否还需要包装一个 {@link IllegalArgumentException } 来确保强制使用传入的 {@code msg } 作为异常信息
	 * @return 如果异常 {@code ex } 为 null，或者不是运行时异常，则自动将其封装为 {@link IllegalArgumentException }；否则返回对应的运行时异常
	 * @since 2.3.0
	 */
	public static final RuntimeException wrapException(final @Nullable String msg, final boolean forceUseMsg, final @Nullable Throwable ex) {
		return wrapException(msg, forceUseMsg, ex, null);
	}

	/**
	 * 将指定的异常信息封装为运行时异常
	 * 
	 * @return 如果异常 {@code ex } 为 null，或者不是运行时异常，则自动将其封装为 {@link IllegalArgumentException }；否则返回对应的运行时异常
	 * @since 2.3.0
	 */
	public static final RuntimeException wrapException(final @Nullable String msg, final @Nullable Throwable ex) {
		return wrapException(msg, false, ex, null);
	}
}
