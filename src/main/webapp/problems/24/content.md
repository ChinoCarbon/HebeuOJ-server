lyn最近在学习一门新的编程语言, 换了新的编辑器, 但是这个编辑器好像有点奇怪.  lyn按照编程规范写了一些代码, 重新打开的时候编辑器竟然将代码放在了一行! 并且好像少了一些括号! 现在lyn不知道这个代码的括号是不是正好匹配的, 他找到了你, 希望你可以帮助他解决这个问题. 你看了一下, 这个编程语言的括号包含小括号(), 中括号[], 大括号{}.

### 输入格式:

第一行一个整数n(<=30) 表示这样的代码数.

之后n行, 每行一个长度不超过50的字符串, 表示编辑器修改之后的代码.

### 输出格式:

对于每一行代码, 如果括号匹配, 输出true; 否则输出false.

### 输入样例:

在这里给出一组输入。例如：

```in
2
[[qwe ]]
({ $lyn )}
```

### 输出样例:

在这里给出相应的输出。例如：

```out
true
false
```