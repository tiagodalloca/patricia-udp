(defproject patricia-udp "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot patricia-udp.core
  :target-path "target/%s"

  :java-source-paths ["src/java"]
  :source-paths ["src/clojure"]

  :profiles {:dev {:repl-options {:init-ns user}}
             :uberjar {:aot :all}})
