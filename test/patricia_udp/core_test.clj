(ns patricia-udp.core-test
  (:require [clojure.test :refer :all]
            [patricia-udp.random.hamburger :refer :all]))

(deftest hamburg
  (testing "Pricing"
    (is (= (-> (new-sandwich)
               (add-ingredient :bread 2)
               (add-ingredient :hamburger 1)
               price)
           5)))
  (testing "Light sandwich"
    (is (= (-> (new-sandwich)
               (add-ingredient :bread 2)
               (add-ingredient :hamburger 1)
               (add-ingredient :lettuce 2)
               price)
           6.3))))
