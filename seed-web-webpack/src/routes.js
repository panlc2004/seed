import Login from './view/Login.vue'
import Index from './view/Index.vue'


const routes = [
    {name: 'index', path: '/', component: Index},
    {name: 'login', path: '/login', component: Login},
];

export default routes;