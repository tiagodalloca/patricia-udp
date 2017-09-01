(ns patricia-udp.tasks
  (:require [patricia-udp.tools :as tools]))

(def ips (->> (range 43 83)
              (map #(str "177.220.18." %))))

(defmulti handle-main (fn [[f]] (keyword f)))

(defmethod handle-main :status
  [args] 
  (->> (pmap #(do [% (tools/reachable? % 500)]) ips) 
       doall
       (map (fn [[ip r?]]
              (.write *out* (str ip ": "
                                 (if r? "Conectado" "Desconectado")
                                 \newline))))
       dosync))

(defmethod handle-main :default
  [args]
  (println args))
