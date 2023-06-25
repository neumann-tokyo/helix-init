(ns helix-init.pages.about
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            ["@chakra-ui/react" :refer [Heading]]))

(defnc about-page []
  (d/div
   ($ Heading {:as "h1" :size "4xl"} "About")))
