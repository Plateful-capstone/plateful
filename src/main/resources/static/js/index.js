import keys from './keys.js';

const client = filestack.init(keys.filestackAPIKey);
client.picker().open();

console.log('yo');

