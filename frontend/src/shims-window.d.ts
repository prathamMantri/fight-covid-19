interface Window {
	MutationObserver?: typeof MutationObserver;
	dataLayer: any[];
	uid: string;
	/* eslint-disable camelcase */
	gtm_id: string;
	gtm_auth: string;
	gtm_preview: string;
	/* eslint-enable camelcase */
	__INITIAL_STATE__: object;
	__PRERENDER_INJECTED: boolean;
}
