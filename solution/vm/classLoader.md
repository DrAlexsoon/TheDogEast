- 加载
> - new / getstatic/putstatic/invokestic
> - lang.reflect包
> - 初始化一个类,发现夫类没有进行初始化
> - main类
- 验证 
> - 文件格式
> - 元数据验证
> - 字节码验证
> - 符号引用验证
- 准备
> 类变量所分配的内存都将在方法区分配
- 解析
> 符号引用(symbolic references)
> 直接引用(direct reference) 可以直接指向目标指针/相对偏移量/句柄
- 初始化
> <clinit>()
#类加载器
**判断两个类是否相等,需要在同一个类加载器加载的前提下才有意义;即使来自同一个class文件,被同一个虚拟机加载.**
> 双亲委派
>> 从虚拟机出发只有两种虚拟机1.Bootstrap ClassLoader(C++实现是虚拟机的一部分)
>> **不是强制性约束模型** 双亲委派破坏
>> 1.不提倡覆盖loadClass方法而是覆盖findClass方法
>> 2.模型缺陷:基础类回调用户类(JNDI/SPI) 线程上下文类加载器(Thread Context ClassLoader) 从父线程继承而来,没有设置默认是Application
>> 3.热部署(hot swap) TODO OSGI


#Stack Frame
> - Local Variable Table
>> Variable Slot 最小单位,虚拟机没有明确规定占用空间的大小,由于局部变量表建立在线程堆栈上,不存在安全问题
```java
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
```
placeholder能否被回收的根本原因是局部变量表中Slot是否还有placeholder的引用
> - Operand Stack
>> 是一个FIFO的栈,操作数栈的每一个元素都是一个java类型,在模型重视独立的,实现中会有一部分重叠
> - Dynamic Linking
>> 每个栈帧都指向一个运行时常量池中栈帧所属的方法引用,为了支持方法调用中的动态链接
>>> - 静态解析: 在类解析阶段会把符号引用转化为直接引用
>>> - 动态解析: 运行期间转化为直接引用
> - Return Address
>> 当方法执行后,只有两种方式可以退出这个方法
>>> - 1.正常完成出口(Normal Method Invocation Completion): 遇到任意一个方法返回字节码指令
>>> - 2.异常完成出口(Abrupt Method Invocation Completion)遇到异常并且在异常表中没有搜索到匹配的异常处理器

###方法调用
> - 解析: 方法在程序执行之前确定调用版本,并且版本在运行期不变(静态方法,私有方法)
> - 分派: **静态分配**的典型应用场景是重载 **动态分配**重写 
```java
//Human (static Type)
//man (Actual Type)
Human man = new Man();
```