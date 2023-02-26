import { IObservableArray, IArrayChange, IArraySplice, IObservableValue, IComputedValue, IValueDidChange, Lambda, ObservableSet, ISetDidChange, ObservableMap, IMapDidChange, IObjectDidChange } from "../internal";
export declare function observe<T>(value: IObservableValue<T> | IComputedValue<T>, listener: (change: IValueDidChange<T>) => void, fireImmediately?: boolean): Lambda;
export declare function observe<T>(observableArray: IObservableArray<T>, listener: (change: IArrayChange<T> | IArraySplice<T>) => void, fireImmediately?: boolean): Lambda;
export declare function observe<V>(observableMap: ObservableSet<V>, listener: (change: ISetDidChange<V>) => void, fireImmediately?: boolean): Lambda;
export declare function observe<K, V>(observableMap: ObservableMap<K, V>, listener: (change: IMapDidChange<K, V>) => void, fireImmediately?: boolean): Lambda;
export declare function observe<K, V>(observableMap: ObservableMap<K, V>, property: K, listener: (change: IValueDidChange<V>) => void, fireImmediately?: boolean): Lambda;
export declare function observe(object: Object, listener: (change: IObjectDidChange) => void, fireImmediately?: boolean): Lambda;
export declare function observe<T, K extends keyof T>(object: T, property: K, listener: (change: IValueDidChange<T[K]>) => void, fireImmediately?: boolean): Lambda;
