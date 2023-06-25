(ns helix-init.pages.top
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            ["@chakra-ui/react" :refer [Heading]]
            [helix-init.components.random-cat :refer [random-cat]]))

(defnc top-page []
  (d/div
   ($ Heading {:as "h1" :size "4xl"} "Signed in Top")
   ($ Heading {:as "h2" :size "3xl"} "Random Cat")
   (d/div
    ($ random-cat))))
