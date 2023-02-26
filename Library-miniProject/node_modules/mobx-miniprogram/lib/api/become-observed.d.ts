import { IObservableArray, IObservable, IComputedValue, ObservableMap, ObservableSet, Lambda } from "../internal";
export declare function onBecomeObserved(value: IObservable | IComputedValue<any> | IObservableArray<any> | ObservableMap<any, any> | ObservableSet<any>, listener: Lambda): Lambda;
export declare function onBecomeObserved<K, V = any>(value: ObservableMap<K, V> | Object, property: K, listener: Lambda): Lambda;
export declare function onBecomeUnobserved(value: IObservable | IComputedValue<any> | IObservableArray<any> | ObservableMap<any, any> | ObservableSet<any>, listener: Lambda): Lambda;
export declare function onBecomeUnobserved<K, V = any>(value: ObservableMap<K, V> | Object, property: K, listener: Lambda): Lambda;
