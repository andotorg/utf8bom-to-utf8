# utf8bom-to-utf8
utf8+bom编码格式 java 文件 转换为 utf8 格式文件，网络上坑比较多我这个就直接使用吧

### 问题
因为在项目原来旧项目转向Maven项目的时候，需要编译，在maven编译的过程中爆出了很多的错误，就说每一个文件前面有特殊字符，一看java文件编码格式为utf-8+bom格式的，所以工作无法进行下去，然后他爆出来了很多的错误，相同文件出现了两次。

> [ERROR] src/main/java/cn/**/**/***/******.java&gt;:[1,10] class;:[1,1] illegal character: '\ufeff'

> [ERROR] src/main/java/cn/**/**/***/******.java&gt;:[1,10] class, interface, or enum expected

我就把这些文件路径复制到一个文件中，然后进行字符串截取，找出文件的真实路径，然后读取进行替换的工作，所以你只需要

> mvn install

就所有的路径爆出来，爆出来之后，把这些路径复制到项目的resources/filepath.txt，然后运行程序，就可以了都替换成功了！

> 文本是一行一行读的，所以复制的时候一定要复制全，然后一个文件有两行，所以我只需要把单数行转换就行了

> 这样做的好处是如果有文件不是+bom的就不需要转换了，如果不是加bom的转换了，就会丢失3个字符
