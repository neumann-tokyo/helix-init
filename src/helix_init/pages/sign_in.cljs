(ns helix-init.pages.sign-in
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc sign-in-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "Sign In")))
