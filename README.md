# Helix Sample

This is a sample code of [helix](https://github.com/lilactown/helix).
I used regular React libraries not ClojureScript culture's one.

## libraries

- [x] Tailwind.css
- [x] helix
- [x] react-router-dom
- [x] jotai
- [x] axios
- [x] react-hook-form
- [x] test by testing-library/react and jsdom

## development

```
npm install
npm run dev
npm run test
```

we cannot use pnpm, unfortunately

## features

- Normal form: [src/helix_init/pages/sign_up.cljs](https://github.com/neumann-tokyo/helix-init/blob/main/src/helix_init/pages/sign_up.cljs)
- React Hook Form: [src/helix_init/pages/sign_in.cljs](https://github.com/neumann-tokyo/helix-init/blob/main/src/helix_init/pages/sign_in.cljs)
  - dummy jwt token authentication with jotai
- Axios, jotai loadable: [src/helix_init/components/random_cat.cljs](https://github.com/neumann-tokyo/helix-init/blob/main/src/helix_init/components/random_cat.cljs)
- [Sample test code](https://github.com/neumann-tokyo/helix-init/blob/main/test/helix_init/components/sample_test.cljs)
