# `find`

>  **<font color="red">注意：</font>**
> linux中要表示符号的原义，需要加上转义符号`\`

## 1、`find -exec {} \;`

`{}`代替`find`结果，`\;`表示分号的作用。

```shell
find -name ".txt" -exec rm {} \;
```

上述语句删除所有的txt文件

## 2、`find -regex pattern`

```shell
find . \( -name "*.txt" -o -name "*.pdf" \)
```

或者

```shell
find . -regex  ".*\(\.txt\|\.pdf\)$"
```

上述两种表达方式均能查找txt和pdf后缀的的文件。

但一般来说，正则表达式为：

```shell
".*(\.txt|\.pdf)$"
```

![](./picture/find_regex.png)

