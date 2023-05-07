(ns helix-init.pages.top
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc top-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "Signed in Top")))
