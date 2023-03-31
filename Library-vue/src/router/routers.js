export default [
    // home
    {
        path: "/home",
        component: (resolve) => require(["@/pages/home/Home"], resolve),
    },
    {
        path: "/",
        component: (resolve) => require(["@/pages/home/Home"], resolve),
    },
    // school
    {
        path: "notification",
        component: (resolve) =>
            require(["@/pages/school/Notification"], resolve),
    },
    {
        path: "library",
        component: (resolve) => require(["@/pages/school/Library"], resolve),
    },
    {
        path: "seat",
        component: (resolve) => require(["@/pages/school/Seat"], resolve),
    },
    // receive
    {
        path: "receive",
        component: (resolve) => require(["@/pages/receive/Receive"], resolve),
    },
    {
        path: "receiveSearch",
        component: (resolve) =>
            require(["@/pages/receive/ReceiveSearch"], resolve),
    },

    // credit
    {
        path: "credit",
        component: (resolve) => require(["@/pages/credit/Credit"], resolve),
    },
    {
        path: "creditSearch",
        component: (resolve) =>
            require(["@/pages/credit/CreditSearch"], resolve),
    },
    // settings
];
