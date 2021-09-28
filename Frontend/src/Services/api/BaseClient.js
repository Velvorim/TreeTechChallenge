import { proxy } from "./proxy";

export class BaseClient {
  _uri = "";

  constructor(uri) {
    this._uri = uri;
  }

  get(id) {
    return proxy.get(this._uri + "/" + id);
  }

  getDescription(description) {
    return proxy.get(this._uri + "/" + description);
  }

  getAll() {
    return proxy.get(this._uri);
  }

  put(source) {
    return proxy.put(this._uri + "/" + source.id, source);
  }

  patch(source) {
    return proxy.patch(this._uri + "/" + source.id, source);
  }

  post(source) {
    return proxy.post(this._uri, source);
  }

  delete(id) {
    return proxy.delete(this._uri + "/" + id);
  }
}
