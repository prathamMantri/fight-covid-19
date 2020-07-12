export default function authHeader() {
	const user = JSON.parse(<string>localStorage.getItem('user'))

	if (user && user.accessToken) {
		return { Authorization: 'Bearer ' + user.accessToken }
	} else {
		return {}
	}
}
