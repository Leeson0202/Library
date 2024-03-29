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
        path: "school",
        component: (resolve) =>
            require(["@/pages/school/School"], resolve),
    },
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
        path: "receivefast",
        component: (resolve) => require(["@/pages/receive/ReceiveFast"], resolve),
    },
    {
        path: "receiveRule",
        component: (resolve) =>
            require(["@/pages/receive/ReceiveRule"], resolve),
    },

    // credit
    {
        path: "credit",
        component: (resolve) => require(["@/pages/credit/Credit"], resolve),
    },
    // feedback
    {
        path: "feedback",
        component: (resolve) => require(["@/pages/feedback/Feedback"], resolve),
    },
    // user
    {
        path: "user",
        component: (resolve) => require(["@/pages/user/User"], resolve),
    },
    // settings
    {
        path: "settings",
        component: (resolve) => require(["@/pages/setting/Settings"], resolve),
    },
];
