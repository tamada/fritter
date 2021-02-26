---
title: ":house: Home"
---

[![build](https://github.com/tamada/fritter/workflows/build/badge.svg)](https://github.com/tamada/fritter/actions?query=workflow%3Abuild)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2efb1d837efe40019652723687ac9173)](https://www.codacy.com/gh/tamada/fritter/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tamada/fritter&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/tamada/fritter/branch/main/graph/badge.svg?token=P1BFSTXHH5)](https://codecov.io/gh/tamada/fritter)
[![Coverage Status](https://coveralls.io/repos/github/tamada/fritter/badge.svg?branch=main)](https://coveralls.io/github/tamada/fritter?branch=main)

[![License](https://img.shields.io/badge/License-Apache%202.0-blue)](https://raw.githubusercontent.com/tamada/fritter/main/LICENSE)
[![Version](https://img.shields.io/badge/Version-1.0.0-blue)](https://github.com/tamada/fritter/releases/tag/v1.0.0)

[![Docker](https://img.shields.io/badge/Docker-ghcr.io%2Ftamada%2Ffritter%3A1.0.0-green?logo=docker)](https://github.com/users/tamada/packages/container/package/fritter)
[![Homebrew](https://img.shields.io/badage/Homebrew-tamada/brew/fritter?logo=homebrew)](https://github.com/tamada/homebrew-brew)

[![Discussion](https://img.shields.io/badge/GitHub-Discussion-orange?logo=github)](https://github.com/tamada/fritter/discussions)

## Overview

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
    

