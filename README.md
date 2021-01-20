# fritter

Small object programming/Object oriented exercise checker.
This product extends [tamada/9rules](https://github.com/tamada/9rules).

## Description

A book titled '[The ThoughtWorks Anthology: Essays on Software Technology and Innovation](https://pragprog.com/book/twa/thoughtworks-anthology)' are published.
Chapter 6 in the book introduces object calisthenics for better software design.
The rules shown in the book are as follows.

1. Use 1 level of indentation per method (`indent_level`),
2. Do not use the `else` keyword (`no_else`),
3. Wrap all primitives and strings (`primitive_wrapping`),
4. Use only 1 dot per line (`one_dot_per_line`),
5. Do not abbreviate (`no_abbrev`),
6. Keep all entities small,
    * 50 lines in a source file (`lins_of_file`),
    * 3 lines in a method (`lines_of_method`), and
    * 10 classses in a package (`classes_in_package`).
7. Do not use any classes with more than 2 instance variables (`field_count`),
8. Use first-class collections (`first_class_collection`), and
9. Do not use any getters/setters/properties (`no_accessor`)

Unfortunately, to confirm obeying the rules is by a human eye.
Therefore, this tool was developed to validate the rules automatically by analyzing given Java source codes.
By the way, this tool is programed to obey above rules.

### Introduced rules.

a. Do not use any methods with more than 2 local variables (`variable_count`),
b. Do not use `static` method except `main` method (`no_static_method`),
c. Do not create an array (`no_new_array`),
d. Do not use `System.exit` (`no_system_exit`), and
e. Do not use `\n` in the `printf` formatter (`no_return_code_in_printf`). 

## Usage

```shell
fritter [OPTIONS] <JAVA_SOURCEs|DIRs...>

OPTIONS:
    -c, --config <CONFIG>           specifies the configuration file.
    -f, --format <FORMAT>           specifies the resultant format. Default is json.
                                    Available values: json, markdown, yaml, and xml.
    -l, --level <LEVEL>             specifies the strict level. Default is default.
                                    Available values: strict, general, rough, and default.
    -s, --no-summary                does not show the summary of analysis.
    -t, --thread <NUMBER>           specifies the number of threads in use.
                                    Default is 0 (not use threads).
    -v, --show-no-violated-files    shows files with no violations.

    -h, --help                      prints this message and exit.
JAVA_SOURCEs    Java source files.
DIRs            Directories including Java source files.
```

## Result example

### Json

```json5
{
   results: [
      {
         "base_directory": "src/main/java",
         "violations": [
            {
               "file": "jp/cafebabe/fritter/utils/CamelCaser.java",
               "messages": [
                  {
                     "line": 4,
                     "key": "indent_level",
                     "message": "too deep indent level: 2"
                  },
                  {
                     "line": [10, 12],
                     "key": "field_count",
                     "message": "too many field count: 4"
                  },
               ]
            }
         ]
      }
   ]
}
```