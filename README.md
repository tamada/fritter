[![build](https://github.com/tamada/fritter/workflows/build/badge.svg)](https://github.com/tamada/fritter/actions?query=workflow%3Abuild)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2efb1d837efe40019652723687ac9173)](https://www.codacy.com/gh/tamada/fritter/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tamada/fritter&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/tamada/fritter/badge.svg?branch=main)](https://coveralls.io/github/tamada/fritter?branch=main)

[![License](https://img.shields.io/badge/License-Apache%202.0-blue)](https://raw.githubusercontent.com/tamada/fritter/main/LICENSE)

[![Discussion](https://img.shields.io/badge/GitHub-Discussion-orange?logo=github)](https://github.com/tamada/fritter/discussions)
[![Docker](https://img.shields.io/badge/Docker-ghcr.io%2Ftamada%2Ffritter%3A1.0.0-orange?logo=docker)](https://github.com/users/tamada/packages/container/package/fritter)

# fritter

Small object programming/Object oriented exercise checker.

This product extends [tamada/9rules](https://github.com/tamada/9rules).
The superior points of `fritter` than 9rules are as follows.

*  Simplify the class relationships from 9rules.
   *  In the case of `9rules`, it becomes to complicated relationships among classes to satisfy the 9rules itself.
   *  Then, `fritter` prioritizes the understandability of the source codes even if to not satisfy the rules of itself.
*  Introduce more rules, and ease to add more rules.
   *  This is the side effect of the above point.
   *  To introduce the new rule, we provide only two classes which are subclasses of `Validator` and `ValidatorService`.
*  Ease to customize the parameters for validators.
   *  All parameters are read from configuration files.
   *  The configuration files are given by `--level` option or `--config` option.  `fritter` defines the parameters by json formatted config file also `level`.
*  Enable to select the output formats.
   *  `fritter` provides `json`, `xml`, and `markdown` format as result.
* Thread supports.
   *  `fritter` uses the threads by user requests for each analyzing target.

## Description

The book titled '[The ThoughtWorks Anthology: Essays on Software Technology and Innovation](https://pragprog.com/book/twa/thoughtworks-anthology)' are published.
Chapter 6 in the book introduces object calisthenics for better software design.
The rules shown in the book are as follows.

1.  Use 1 level of indentation per method (`indent_level`), `DONE` 
2.  Do not use the `else` keyword (`no_else`), `DONE`
3.  Wrap all primitives and strings (`primitive_wrapping`), `DONE`
4.  Use only 1 dot per line (`one_dot_per_line`), `DONE`
5.  Do not abbreviate (`no_abbrev`),
6.  Keep all entities small,
    *  50 lines in a source file (`lines_of_class`), `DONE`
    *  3 lines in a method (`lines_of_method`), and `DONE`
    *  10 classses in a package (`classes_in_package`).
7.  Do not use any classes with more than 2 instance variables (`field_count`), `DONE`
8.  Use first-class collections (`first_class_collection`), and `DONE`
9.  Do not use any getters/setters/properties (`no_accessor`) `DONE`

Unfortunately, to confirm obeying the rules is by a human eye.
Therefore, this tool was developed to validate the rules automatically by analyzing given Java source codes.
By the way, this tool is programed to obey above rules.

### Introduced rules

10.  Do not use any methods with more than 2 local variables (`local_variable_count`), `DONE`
11.  Do not use `static` method except `main` method (`no_static_method`), `DONE`
12.  Do not create an array (`no_new_array`), `DONE`
13.  Do not use `System.exit` (`no_system_exit`) except `main` method, `DONE`
14.  Do not use `\n` in the `printf` formatter (`no_return_code_in_printf`), and `DONE`
15.  Do not use only single character in the variable name, except loop control variables (`single_character_name`). `DONE`

## Usage

```shell
Usage: fritter [-hinstV] [-c=CONFIG] [-f=FORMAT] [-l=LEVEL]
               SOURCE_FILEs|SOURCE_DIRs...
Small object programming checker.
      SOURCE_FILEs|SOURCE_DIRs...
                            Java source files and/or directories containing
                              Java source files.
  -c, --config=CONFIG       specifies the configuration file.
  -f, --format=FORMAT       specifies the resultant format. Default is json.
                            Available values: json, markdown, yaml, and xml
  -h, --help                Show this help message and exit.
  -i, --no-validator-info   does not show the validator information for
                              analyses.
  -l, --level=LEVEL         specifies the strict level. Default is default.
                            Available values: strict, general, rough, and
                              default.
  -n, --no-summary          does not show the summary of analysis.
  -s, --show-no-violated-files
                            shows file names with no violations.
  -t, --with-threads        use threads for validations.
  -V, --version             Print version information and exit.
```

## Result example

### Json

The following json is validating `src/test/resources/projects/examples/HelloWorld.java` with no options, and formatted with `jq` command.

```json5
{
   "date": "2021-02-01T23:37:08.966582",
   "validators": [
      "indent_level",
      "no_else",
      "primitive_wrapping",
      "dot_count_per_line",
      "lines_of_class",
      "lines_of_method",
      "field_count",
      "first_class_collection",
      "no_accessor",
      "no_static_method",
      "no_new_array",
      "no_system_exit",
      "no_return_code_in_printf",
      "single_character_name"
   ],
   "results": [
      {
         "base": "src/test/resources/projects/examples/HelloWorld.java",
         "violations": [
            {
               "file": "",
               "messages": [
                  {
                     "line": 10,
                     "key": "lines_of_method",
                     "message": "lines of method is 4, more than 3"
                  },
                  {
                     "line": 19,
                     "key": "lines_of_method",
                     "message": "lines of method is 4, more than 3"
                  },
                  {
                     "line": 15,
                     "key": "no_accessor",
                     "message": "getMessage: no getter method"
                  },
                  {
                     "line": 2,
                     "key": "no_static_method",
                     "message": "violatedMethod: no static method except main method"
                  },
                  {
                     "line": 12,
                     "key": "no_system_exit",
                     "message": "no System.exit except main method"
                  },
                  {
                     "line": 11,
                     "key": "no_return_code_in_printf",
                     "message": "no '\n', use '%n' in printf"
                  }
               ]
            }
         ]
      }
   ],
   "summary": {
      "violated-files": 1,
      "total-files": 1,
      "violated-file-count": 6
   }
}
```

### Xml

The following xml is validating `src/test/resources/projects/examples/Primes.java` with `-f xml` options, and formatted with `xmllint` command (`xmllint --format -`).

```xml
<?xml version="1.0"?>
<fritter>
   <validators>
      <validator>indent_level</validator>
      <validator>no_else</validator>
      <validator>primitive_wrapping</validator>
      <validator>dot_count_per_line</validator>
      <validator>lines_of_class</validator>
      <validator>lines_of_method</validator>
      <validator>field_count</validator>
      <validator>first_class_collection</validator>
      <validator>no_accessor</validator>
      <validator>no_static_method</validator>
      <validator>no_new_array</validator>
      <validator>no_system_exit</validator>
      <validator>no_return_code_in_printf</validator>
      <validator>single_character_name</validator>
   </validators>
   <results>
      <result>
         <base-directory>src/test/resources/projects/examples/Primes.java</base-directory>
         <files>
            <file>
               <file-name/>
               <violations>
                  <violation>
                     <validator-type>indent_level</validator-type>
                     <location>
                        <line-number>17</line-number>
                     </location>
                     <message>print: indent level is 2, more than 1</message>
                  </violation>
                  <violation>
                     <validator-type>indent_level</validator-type>
                     <location>
                        <line-number>29</line-number>
                     </location>
                     <message>sieves: indent level is 2, more than 1</message>
                  </violation>
                  <violation>
                     <validator-type>no_else</validator-type>
                     <location>
                        <line-number>51</line-number>
                     </location>
                     <message>no else statement</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_class</validator-type>
                     <location>
                        <line-number>3</line-number>
                     </location>
                     <message>lines of class is 55, more than 50</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_method</validator-type>
                     <location>
                        <line-number>4</line-number>
                     </location>
                     <message>lines of method is 5, more than 3</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_method</validator-type>
                     <location>
                        <line-number>10</line-number>
                     </location>
                     <message>lines of method is 15, more than 3</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_method</validator-type>
                     <location>
                        <line-number>26</line-number>
                     </location>
                     <message>lines of method is 8, more than 3</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_method</validator-type>
                     <location>
                        <line-number>35</line-number>
                     </location>
                     <message>lines of method is 5, more than 3</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_method</validator-type>
                     <location>
                        <line-number>41</line-number>
                     </location>
                     <message>lines of method is 5, more than 3</message>
                  </violation>
                  <violation>
                     <validator-type>lines_of_method</validator-type>
                     <location>
                        <line-number>47</line-number>
                     </location>
                     <message>lines of method is 6, more than 3</message>
                  </violation>
                  <violation>
                     <validator-type>no_new_array</validator-type>
                     <location>
                        <line-number>42</line-number>
                     </location>
                     <message>do not use array, use List instead</message>
                  </violation>
               </violations>
            </file>
         </files>
      </result>
   </results>
   <summary>
      <violated-file-count>1</violated-file-count>
      <target-file-count>1</target-file-count>
      <violation-count>11</violation-count>
   </summary>
</fritter>
```

### Markdown

The following markdown is validating `src/test/resources/projects/examples/StatsValues.java` with `-f markdown` options.

```markdown
# fritter results

Date: 2021-02-01T23:47:02.609847

## Validators

* indent_level
* no_else
* primitive_wrapping
* dot_count_per_line
* lines_of_class
* lines_of_method
* field_count
* first_class_collection
* no_accessor
* no_static_method
* no_new_array
* no_system_exit
* no_return_code_in_printf
* single_character_name

## Results

### src/test/resources/projects/examples/StatsValues.java

#### 

##### Violations

* indent_level (line: 23)
    * print: indent level is 2, more than 1
* lines_of_method (line: 13)
    * lines of method is 5, more than 3
* lines_of_method (line: 19)
    * lines of method is 9, more than 3
* lines_of_method (line: 29)
    * lines of method is 5, more than 3
* lines_of_method (line: 35)
    * lines of method is 4, more than 3
* lines_of_method (line: 40)
    * lines of method is 5, more than 3
* field_count (lines: 9, 10, 11)
    * field count is 3, more than 2
* first_class_collection (lines: 9, 10, 11)
    * not first class collection

## Summary

* violated files: 1
* total files: 1
* violated file count: 8
```
