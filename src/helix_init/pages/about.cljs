(ns helix-init.pages.about
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc about-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "About")))
