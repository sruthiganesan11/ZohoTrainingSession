import axios from "axios";
import { getToken } from "./AuthService";

const BASE_REST_API_URL = "http://localhost:8081/api/employee";

axios.interceptors.request.use(
  function (config) {
    config.headers["Authorization"] = getToken();
    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);
export const getAllEmployee = () => axios.get(BASE_REST_API_URL);

export const getEmployee = (id) => axios.get(BASE_REST_API_URL + "/" + id);

export const addEmployee = (employee) =>
  axios.post(BASE_REST_API_URL, employee);

export const updateEmployee = (id, employee) =>
  axios.put(BASE_REST_API_URL + "/" + id, employee);

export const deleteEmployee = (id) =>
  axios.delete(BASE_REST_API_URL + "/" + id);