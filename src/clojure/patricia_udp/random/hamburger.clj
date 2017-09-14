(ns patricia-udp.random.hamburger)

(def ingredient-price
  {:bread 1
   :hamburger 3
   :lettuce 1
   :bacon 2
   :cheese 3
   :tomatoe 1})

(defn new-sandwich []
  (->> ingredient-price keys (reduce (fn [a k] (conj a k 0)) [])
       (apply hash-map)))

(defn add-ingredient [h i n]
  (update h i (partial + n)))

(defn price [s]  
  (->> s (reduce (fn [a [k v]] (+ a (* (k ingredient-price) v))) 0)
       #(if (and (> (:lettuce s) 2)
                 (zero? (:bacon s)))
          (* 0.9 %))))
