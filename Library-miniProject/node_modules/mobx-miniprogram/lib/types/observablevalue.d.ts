import { Lambda, IInterceptor, Atom, IInterceptable, IListenable, IEnhancer, IEqualsComparer } from "../internal";
export interface IValueWillChange<T> {
    object: any;
    type: "update";
    newValue: T;
}
export interface IValueDidChange<T> extends IValueWillChange<T> {
    oldValue: T | undefined;
}
export interface IObservableValue<T> {
    get(): T;
    set(value: T): void;
    intercept(handler: IInterceptor<IValueWillChange<T>>): Lambda;
    observe(listener: (change: IValueDidChange<T>) => void, fireImmediately?: boolean): Lambda;
}
export declare class ObservableValue<T> extends Atom implements IObservableValue<T>, IInterceptable<IValueWillChange<T>>, IListenable {
    enhancer: IEnhancer<T>;
    name: string;
    private equals;
    hasUnreportedChange: boolean;
    interceptors: any;
    changeListeners: any;
    value: any;
    dehancer: any;
    constructor(value: T, enhancer: IEnhancer<T>, name?: string, notifySpy?: boolean, equals?: IEqualsComparer<any>);
    private dehanceValue;
    set(newValue: T): void;
    private prepareNewValue;
    setNewValue(newValue: T): void;
    get(): T;
    intercept(handler: IInterceptor<IValueWillChange<T>>): Lambda;
    observe(listener: (change: IValueDidChange<T>) => void, fireImmediately?: boolean): Lambda;
    toJSON(): T;
    toString(): string;
    valueOf(): T;
}
export declare const isObservableValue: (x: any) => x is IObservableValue<any>;
