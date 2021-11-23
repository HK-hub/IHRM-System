
你好，欢迎您: ${username}
<#-- if 指令 -->
<#if flag=1>
    传入数据=1
    <#elseif flag=2>
        传入数据=2
    <#elseif flag=3>
        传入数据=3
    <#else >
        传入数据=其他：${flag}
</#if>


<#-- 循环 list ： 循环迭代
           数组名称 as 别名

 -->
<#list weeks as days>
    ${days}
</#list>


<#-- 模板包含： include 指令  -->
<#--
    <#include "模板名称.ftl">

-->


<#--  assign 指令： 在模板中定义数据存入 root 节点下  -->
<#assign name="zhangsang">

root 节点下的数据赋值: ${name}



<#-- 函数：在变量名后面加 ?函数名 ： ${variables?function} -->
${username?lower_case}