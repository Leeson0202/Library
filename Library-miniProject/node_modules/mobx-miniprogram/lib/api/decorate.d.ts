export declare function decorate<T>(clazz: new (...args: any[]) => T, decorators: {
    [P in keyof T]?: MethodDecorator | PropertyDecorator | Array<MethodDecorator> | Array<PropertyDecorator>;
}): void;
export declare function decorate<T>(object: T, decorators: {
    [P in keyof T]?: MethodDecorator | PropertyDecorator | Array<MethodDecorator> | Array<PropertyDecorator>;
}): T;
