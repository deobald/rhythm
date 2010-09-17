(ns rhythm.core
  (:use clojure.contrib.def))

(defn welcome []
  (println "To answer a question, type (answer XYZ), where XYZ is your answer to the question.")
  (println "You can use this for short: (a XYZ)")
  (println "My questions will appear in quotes.")
  (println "A list begins with an open-parenthesis and ends with a close-parenthesis."))

(def next-answer (ref ()))
(def number (ref 0))

(defmacro s [form]
  `(dosync (ref-set next-answer '~form)))

(defn question-1 []
  (s ())
  "1. What is an empty list?")

(defn question-2 []
  (s (+ 2 3))
  (println "Functions are called but placing them first in a list: (FUNCTION parameter1 parameter2 ...)")
  (println "Functions operate on any parameters given to them.")
  "2. How might you add 2 and 3 together?")

(defn no-more-questions []
  "Sorry, out of questions!")

(defn question [n]
  (dosync (ref-set number n))
  (println "\n")
  (condp = n
    1 question-1
    2 question-2
    no-more-questions))

(defn start []
  (welcome)
  ((question 1)))

(defmacro answer [form]
  (if (= @next-answer `~form)
    (do
      (let [next-question (+ 1 @number)]
        (println (str "Correct! On to question " next-question))
        ((question next-question))))
    (do
      (println (str "Sorry, not quite. Try question " @number " again."))
      ((question @number)))))

(defalias a answer)
