#!/usr/bin/env bash
# stop previous webpack server
./gradlew stop
# initial "slow" build: prepare dependencies, run webpack
./gradlew compileKotlin2Js copyJsToWebpack run
# continuous "fast" build
./gradlew -t -x packages compileKotlin2Js copyJsToWebpack
