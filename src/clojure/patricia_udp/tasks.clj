(ns patricia-udp.tasks
  (:require [patricia-udp.tools :as tools])
  ;; (:import [PatriciaUDP Utils])
  )

;; (def ips (->> (range 43 83)
;;               (map #(str "177.220.18." %))
;;               vec))

(def ips-and-macs {"177.220.18.43" nil
                   "177.220.18.44" "2c-44-fd-fa-fa-01"
                   "177.220.18.45" "2c-44-fd-fa-ca-c2"
                   "177.220.18.46" "2c-44-fd-fa-ea-dc"
                   "177.220.18.47" "2c-44-fd-fa-ba-ca"
                   "177.220.18.48" "2c-44-fd-fa-da-a9"
                   "177.220.18.49" "2c-44-fd-fa-ea-f2"
                   "177.220.18.50" "2c-44-fd-fa-ca-35"
                   "177.220.18.51" "2c-44-fd-fa-ba-e1"
                   "177.220.18.52" "2c-44-fd-fa-ba-aa"
                   "177.220.18.53" "2c-44-fd-fa-da-9c"
                   "177.220.18.54" "2c-44-fd-fa-ea-18"
                   "177.220.18.55" "2c-44-fd-fa-da-db"
                   "177.220.18.56" "2c-44-fd-fa-da-bd"
                   "177.220.18.57" "2c-44-fd-fa-f8-cf"
                   "177.220.18.58" "2c-44-fd-fa-da-88"
                   "177.220.18.59" "2c-44-fd-fa-ba-fe"
                   "177.220.18.60" "2c-44-fd-fa-ea-0e"
                   "177.220.18.61" "64-51-06-4f-7b-62"
                   "177.220.18.62" "2c-44-fd-fa-ea-08"
                   "177.220.18.63" "2c-44-fd-fa-ca-44"
                   "177.220.18.64" nil
                   "177.220.18.65" "2c-44-fd-fa-fa-12"
                   "177.220.18.66" "2c-44-fd-fa-da-eb"
                   "177.220.18.67" "2c-44-fd-fa-ea-ca"
                   "177.220.18.68" "2c-44-fd-fa-da-f6"
                   "177.220.18.69" "2c-44-fd-fa-fa-28"
                   "177.220.18.70" "2C-44-fd-fa-09-6d"
                   "177.220.18.71" "2c-44-fd-fa-da-ff"
                   "177.220.18.72" "2C-44-fd-fa-ea-dd"
                   "177.220.18.73" "2c-44-fd-fa-ba-fc"
                   "177.220.18.74" "2c-44-fd-fa-09-35"
                   "177.220.18.75" nil
                   "177.220.18.76" "2c-44-fd-fa-da-f4"
                   "177.220.18.77" "10-60-4b-77-94-01"
                   "177.220.18.78" nil
                   "177.220.18.79" "2c-44-fd-fa-ba-ac"
                   "177.220.18.80" "2c-44-fd-fa-da-3b"
                   "177.220.18.81" "2c-44-fd-fa-ca-07"
                   "177.220.18.82" "2c-44-fd-fa-ea-cf"
                   "177.220.18.83" "2C-44-fd-fa-ca-13"})

(def ips (keys ips-and-macs))

(def default-port 9)

(defmulti handle-main (fn [[f]] (keyword f)))

(defmethod handle-main :status
  [args] 
  (doseq [[ip r?] (->> (pmap #(do [% (tools/reachable? % 500)]) ips) 
                       (sort-by first))] 
    (println ip ": " (if r?
                       "Conectado"
                       "Desconectado"))))

(defmethod handle-main :wol
  [[_ ip]] 
  (if-let [mac (ips-and-macs ip)]
    (let [addr (tools/inet-address-by-name ip)
          ds (tools/create-datagram-socket)
          msg (-> mac PatriciaUDP.Utils/getDPBytes
                  (tools/message addr default-port))]
      (.send ds msg)
      (.close ds))
    (println "tal pc não está disponível")))

(defmethod handle-main :default
  [args]
  (println args))
