(ns rhythm.test.core
  (:use [rhythm.core] :reload)
  (:use [clojure.test]))

(deftest t-compares-forms-against-those-set-with-s
  (is (do (s (same-form)) (t (same-form))) "Forms were not equal."))

