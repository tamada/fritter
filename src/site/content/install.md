---
title: ":anchor: Install"
---

## :beer: Homebrew

[![tamada/brew](https://img.shields.io/badge/Homebrew-tamada%2Fbrew%2Ffritter-orange?logo=homebrew)](https://github.com/tamada/fritter)

Type the following command.

```shell
$ brew tap tamada/brew
$ brew install fritter
```

## :whale: Docker

[![Docker](https://img.shields.io/badge/Docker-ghcr.io%2Ftamada%2Ffritter%3A1.0.0-orange?logo=docker)](https://github.com/users/tamada/packages/container/package/fritter)

```shell
$ docker pull ghcr.io/tamada/fritter:1.0.0 
```

## :muscle: Install yourself

[![tamada/fritter](https://img.shields.io/badge/GitHub-tamada%2Ffritter-orange?logo=github)](https://github.com/tamada/fritter)


```shell
$ git clone https://github.com/tamada/fritter.git
$ cd fritter
$ mvn package
```

## :brief: Requirements

* Runtime environment
  * Java 11 or later
* Build tools
  * Maven 3.x
* Dependencies
  * [JavaParser](https://github.com/javaparser/javaparser)
  * [vavr.io 0.10.0](https://www.vavr.io)
  * [picocli](https://picocli.info/)
  * [gson](https://github.com/google/gson)
* Dependencies (for Unit Tests)
  * [JUnit 5](https://junit.org/junit5/)