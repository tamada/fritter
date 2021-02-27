---
title: ":runner: Usage"
---

### :bicyclist: CLI

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

### :whale: Docker

The docker container images are provided at GitHub Container Image Registry as the following repository.

* [`ghcr.io/tamada/fritter`](https://github.com/users/tamada/packages/container/package/fritter)
    * `1.0.0`, `latest`

To run `fritter` on the docker container, type the following command.

```shell
docker run -it -v $PWD:/home/fritter ghcr.io/tamada/fritter:1.0.0 [OPTIONS] <ARGUMENTS> 
```

options and arguments are same as CLI mode.

## :shoe: Config format

The format of config file must be JSON, like as follows.

```json
{
  "name": "default",
  "validators": [
    "indent_level",
    "no_else",
    "primitive_wrapping",
    "dot_count_per_line",
    "no_abbrev",
    "lines_of_class",
    "lines_of_method",
    "classes_in_package",
    "field_count",
    "first_class_collection",
    "no_accessor",
    "variable_count",
    "no_static_method",
    "no_new_array",
    "no_system_exit",
    "no_return_code_in_printf",
    "single_character_name"
  ],
  "parameters": {
    "indent_level": 1,
    "dot_count_per_line": 1,
    "lines_of_class": 50,
    "lines_of_method": 3,
    "classes_in_package": 10,
    "field_count": 2,
    "variable_count": 2
  }
}
```

## :tshirt: Result example

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

#### Violations

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

