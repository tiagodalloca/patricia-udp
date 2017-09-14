(ns patricia-udp.core
  (:require [patricia-udp.tasks :refer [handle-main]])
  (:gen-class))

(defn -main 
  [& args]
  (handle-main (vec args)))
