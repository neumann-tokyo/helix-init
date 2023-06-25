(ns helix-init.pages.sign-up
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            ["@chakra-ui/react" :refer [FormControl FormLabel FormErrorMessage Input Button Heading]]
            ["react-router-dom" :refer [useNavigate]]))

(defnc sign-up-page []
  (let [navigate (useNavigate)
        [api-errors set-api-errors] (hooks/use-state nil)
        on-submit (fn [e]
                    (.preventDefault e)
                    (let [_email (-> e .-target .-email .-value)
                          password (-> e .-target .-password .-value)
                          password-confirmation (-> e .-target .-password .-value)]
                      (if (not= password password-confirmation)
                        (set-api-errors ["Password confirmation does not match."])
                        (navigate "/"))))]
    (d/div
     ($ Heading {:as "h1" :size "4xl"} "Sign Up")

     (d/form
      {:on-submit on-submit}
      ($ FormControl {:width "300px"}
         ($ FormLabel "Email")
         ($ Input {:type "email"
                   :name "email"
                   :id "email"
                   :placeholder "Email"
                   :required true})

         ($ FormLabel "Password")
         ($ Input {:type "password"
                   :name "password"
                   :id "password"
                   :placeholder "Password"
                   :required true})

         ($ FormLabel "Password Confirmation")
         ($ Input {:type "password"
                   :name "password-confirmation"
                   :id "password"
                   :placeholder "Password Confirmation"
                   :required true})

         (map-indexed (fn [index error]
                        ($ FormErrorMessage {:key index} error))
                      api-errors)
         ($ Button {:mt 4
                    :colorScheme "teal"
                    :type "submit"}
            "Sign In"))))))
