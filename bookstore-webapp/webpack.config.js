module.exports = {
    entry: './src/client/index.jsx',
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.jsx$/,
                exclude: /node_modules/,
                loader: 'babel-loader',
            },
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            }
        ]
    },
    resolve: {
        extensions: [
            '.json',
            '.js',
            '.jsx'
        ]
    },
    output: {
        path: `${__dirname}/public`,
        filename: 'bundle.js'
    }
}