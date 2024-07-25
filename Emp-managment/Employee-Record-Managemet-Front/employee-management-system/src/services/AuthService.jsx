import axios from "axios";

const AUTH_REST_API_URL = "http://localhost:8081/api/auth";

export const registerAPICall = (registerObj) =>
  axios.post(AUTH_REST_API_URL + "/register", registerObj);

export const loginAPICall = (username, password) =>
  axios.post(AUTH_REST_API_URL + "/login", { username, password });

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username, role) => {
  sessionStorage.setItem("authenticatedUser", username);
  sessionStorage.setItem("role", role);
};
export const isUserLoggedIn = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  if (username == null) {
    return false;
  } else {
    return true;
  }
};
export const getLoggedInUser = () => {
  return username;
};

export const logout = () => {
  localStorage.clear();
  sessionStorage.clear();
};

export const isAdminUser = () => {
  let role = sessionStorage.getItem("role");
  if (role != null && role == "ROLE_ADMIN") {
    return true;
  } else {
    return false;
  }
};