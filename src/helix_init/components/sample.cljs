(ns helix-init.components.sample
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc sample []
  (d/div "This is a sample component."))

