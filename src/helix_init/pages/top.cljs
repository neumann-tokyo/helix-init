(ns helix-init.pages.top
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix-init.components.random-cat :refer [random-cat]]))

(defnc top-page []
  (d/div
   (d/h1 {:class-name "text-4xl"} "Signed in Top")
   (d/h2 {:class-name "text-3xl"} "Random Cat")
   (d/div
    ($ random-cat))))
