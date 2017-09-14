(ns patricia-udp.tools
  (:import [java.net InetAddress DatagramPacket DatagramSocket]))

(defn localhost [] (.getLocalHost InetAddress))

(defn inet-address [s] (.getByName InetAddress s))

(defn message [text port]
  (DatagramPacket. (.getBytes text)
                   (.length text)
                   (localhost)
                   port))

(defn create-udp-server [port]
  (DatagramSocket. port))

(defn send-message [text server port]
  (.send server (message text port)))

(defn stop-udp-server [server]
  (.close server))

(defn create-datagram-socket [] (DatagramSocket.))

(defn reachable? [ip timeout] 
  (-> ip InetAddress/getByName (.isReachable timeout)))

(defn empty-message [n]
  (DatagramPacket. (byte-array n) n))

(defn message [abytes address port]
  (DatagramPacket. abytes (alength abytes) address port))

(defn inet-address-by-name [name]
  (InetAddress/getByName name))

;; (defn start-print-loop []
;;   (loop []
;;     (let [orig-packet (empty-message 1024)]
;;       (.receive @udp-server orig-packet)
;;       (.send @udp-server orig-packet)
;;       (let [data(.getData orig-packet)]
;;                                         ; so you can stop repl and play with messages
;;         (swap! state conj data)
;;         (println (String. (.getData orig-packet) "UTF-8"))))
;;     (recur)))

