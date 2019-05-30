#!/usr/bin/env groovy

def call(String file) {
    def m = [:]
    def lines = new File(file).readLines()
    for(line in lines) {
        def kv = line.split("=")
        if(kv.length == 2) {
            def k = kv[0]
            def v = kv[1]
            m[k] = v
        }
    }
    return m
}