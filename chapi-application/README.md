# Frontend API

## sample for restful api

1. identify all TypeScript code to nodes
2. create function map by identify axios and such as `axios.get()`
3. generate function map

### Component API call

support framework:

- React
- HTTP request
  - axios
  - umi-request
- 

todo:

- [ ] call level by config (current only support for two levels)
- [ ] Vue
- [ ] Angular

### Axios Sample

```
export function queryScannerConfig() {
  return axios<ScannerConfigType>({
    baseURL,
    url: '/config',
    method: "GET",
  });
}
```

component  

```
import { queryScannerConfig } from "../scanner-api.js"

const ScannerComponent = () => {
   function emo() {
     queryScannerConfig()
   }  
   
  return ()
}
```
