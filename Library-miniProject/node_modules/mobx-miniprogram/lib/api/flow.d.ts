export declare type CancellablePromise<T> = Promise<T> & {
    cancel(): void;
};
export interface FlowYield {
    "!!flowYield": undefined;
}
export interface FlowReturn<T> {
    "!!flowReturn": T;
}
export declare type FlowReturnType<R> = IfAllAreFlowYieldThenVoid<R extends FlowReturn<infer FR> ? FR extends Promise<infer FRP> ? FRP : FR : R extends Promise<any> ? FlowYield : R>;
export declare type IfAllAreFlowYieldThenVoid<R> = Exclude<R, FlowYield> extends never ? void : Exclude<R, FlowYield>;
export declare function flow<R, Args extends any[]>(generator: (...args: Args) => IterableIterator<R>): (...args: Args) => CancellablePromise<FlowReturnType<R>>;
