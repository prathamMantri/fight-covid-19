module.exports = {
	root: true,
	env: {
		node: true
	},
	extends: [
		'plugin:vue/essential',
		'@vue/standard',
		'@vue/typescript/recommended'
	],
	parserOptions: {
		ecmaVersion: 2020
	},
	rules: {
		quotes: [2, 'single', 'avoid-escape'],
		'padded-blocks': 0,
		'comma-style': 0,
		'no-multiple-empty-lines': 0,
		'comma-dangle': 0,
		'comma-spacing': 0,
		'spaced-comment': 0,
		'func-call-spacing': 0,
		'guard-for-in': 0,
		'arrow-parens': [1, 'as-needed'],
		'key-spacing': 0,
		'prefer-arrow-callback': 0,
		'quote-props': ['error', 'as-needed'],
		'no-console': process.env.NODE_ENV === 'production' ? 'off' : 'off',
		'no-debugger': process.env.NODE_ENV === 'production' ? 'off' : 'off',
		'no-tabs': 0,
		'no-mixed-spaces-and-tabs': [1, 'smart-tabs'],
		'no-mixed-operators': 0,
		'no-eval': 0,
		indent: [1, 'tab', {
			SwitchCase: 1,
			VariableDeclarator: 1

		}],
		'no-trailing-spaces': 0,
		typedef: 0,
		'no-implicit-coercion': 0,
		'no-any': 0,
		'object-curly-spacing': 0,
		'prefer-const': 1,
		'space-before-function-paren': 0,
		'space-before-blocks': ['warn', 'always'],
		'no-return-assign': 0,
		'space-in-parens': 0,
		// 'spaced-comment': 0,
		// 'spaced-line-comment': 0,
		// vue rules
		'vue/script-indent': [
			1, 'tab', {
				baseIndent: 0,
				switchCase: 1,
				ignores: []
			}
		],
		'vue/html-indent': [
			1, 'tab', {
				attribute: 1,
				baseIndent: 1,
				closeBracket: 0,
				alignAttributesVertically: true,
				ignores: []
			}
		],
		'vue/name-property-casing': 0,
		'vue/require-default-prop': 0,
		'vue/no-unused-vars': 'off',
		'vue/html-closing-bracket-spacing': 1,
		/*'ter-indent': [
      1,
      'tab',
      {
        SwitchCase: 1
      }
    ]*/
	},
}
