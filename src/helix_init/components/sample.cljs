(ns helix-init.components.sample
  (:require [helix.core :refer [defnc $]]
            ["@chakra-ui/react" :refer [Text]]))

(defnc sample []
  ($ Text "This is a sample component."))

