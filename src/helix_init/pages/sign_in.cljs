(ns helix-init.pages.sign-in
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            ["@chakra-ui/react" :refer [FormControl FormLabel FormErrorMessage Input Button]]
            ["react-hook-form" :refer [useForm]]
            ["jotai" :refer [useAtom]]
            ["js-cookie" :as Cookies]
            [helix-init.atoms :refer [sign-in-token-atom]]))

;; https://github.com/dvingo/malli-react-hook-form
(defnc sign-in-page []
  (let [form (useForm)
        register (.-register form)
        handleSubmit (.-handleSubmit form)
        errors (-> form .-formState .-errors)
        is-submitting (-> form .-formState .-isSubmitting)
        register-email (register "email" #js {:required true})
        register-password (register "password" #js {:required true})
        [api-errors set-api-errors] (hooks/use-state nil)
        [_sign-in-token set-sign-in-token] (useAtom sign-in-token-atom)
        on-submit (fn [data]
                    (if (and (= (.-email data) "user1@example.com")
                             (= (.-password data) "password"))
                      (do (set-sign-in-token (fn [_token] "sign-in-token"))
                          (.set Cookies "sign-in-token" "sign-in-token"))
                      (set-api-errors ["Invalid email or password."])))]
    (d/form
     {:on-submit (handleSubmit on-submit)}

     ($ FormControl {:width "300px"}
        ($ FormLabel "Email")
        ($ Input {:type "email"
                  :name (.-name register-email)
                  :onBlur (.-onBlur register-email)
                  :onChange (.-onChange register-email)
                  :ref (.-ref register-email)})
        (when (.-email errors)
          ($ FormErrorMessage "This field is required."))

        ($ FormLabel "Password")
        ($ Input {:type "password"
                  :name (.-name register-password)
                  :onBlur (.-onBlur register-password)
                  :onChange (.-onChange register-password)
                  :ref (.-ref register-password)})
        (when (.-password errors)
          ($ FormErrorMessage "This field is required."))

        (map-indexed (fn [index error]
                       ($ FormErrorMessage {:key index} error))
                     api-errors)
        ($ Button {:mt 4
                   :colorScheme "teal"
                   :isLoading is-submitting
                   :type "submit"}
           "Sign In")))))
