export async function authFetch(session, url, options) {
  options.headers.authorization = "Bearer " + session.accessToken;
  var rawResponse = await fetch(url, options);

  if (rawResponse.status == 403) {
    localStorage.accessToken = session.refresh();
    options.headers.authorization = session.accessToken;
    rawResponse = await fetch(url, options);
  }

  return rawResponse;
}