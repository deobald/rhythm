(ns rhythm.core
  (:use clojure.contrib.def))

(defn welcome []
  (println "To talk to me, type (answer XYZ), where XYZ is your answer to the question.")
  (println "You can use this for short: (a XYZ)")
  (println "My questions will appear in quotes.")
  (println "A list begins with an open-parenthesis and ends with a close-parenthesis."))

(def next-answer (ref ()))
(def number (ref 0))

(defmacro s [form]
  (dosync (ref-set next-answer `~form))
  nil)

(defmacro check [attempt]
  (= @next-answer `~attempt))

(defn question-1 []
  (s ())
  "1. What is an empty list?")
  
(defn question [n]
  (dosync (ref-set number n))
  (println "\n")
  (condp = n
    1 question-1
    "Sorry, out of questions!"))

(defn start []
  (welcome)
  ((question 1)))

(defmacro answer [form]
  (if (= @next-answer `~form)
    (do
      (println "Correct!")
      (question (+ 1 @number)))
    (do
      (println "Sorry, not quite.")
      (question @number))))

(defalias a answer)
