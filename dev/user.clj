(ns user
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.pprint :refer (pprint)]
            [clojure.repl :as repl]
            [clojure.test :as test]
            [clojure.tools.namespace.repl :refer (refresh refresh-all)]

            [patricia-udp.core :as core]
            [patricia-udp.tools :as tools]
            [patricia-udp.tasks :as tasks]))

(defonce server (atom nil))
(def port 3000)

(defn init! [])

(defn start! [] (-> @server .start))

(defn stop! [] (.stop @server))

(defn restart! [] (.stop @server) (.start @server))

