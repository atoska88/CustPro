CustPro
=======

Custom propery loader library

What is this library?

This library was made to parse files containing key-value pairs with a non general structure. With this library very special files can be parsed by creating the right configuration, for example simple XML files as well.

The main goal was to parse localization files for some mobile platforms, for example Android or iOS. With two special configurations both platforms' localization files can be parsed with the library.

By configuring a new library, (hopefully) any kind of files - containing simple key-value pairs - can be parsed. The process uses so called affixes for the parsing. An affix is constructed from a prefix and a suffix. A file structure can be exactly described by some affixes: one for the whole file, one for a single key-value pair, one for the key of the pair and one for the value. Besides these affixes there are some other control characters, strings: the key-value separator and the line terminator.

There are two kind of affix implemented at the moment:
	- The literal affix: this affix is made by literals which means that a concrete String means the prefix and suffix.
	- The regex affix: this affix is mady by regular expressions, which will be matched by the parsing process. With this approach each message-value pair can be different somehow, if the difference can be written in a regular expression.

A few examples can be found in the Wiki section with sample configurations and code snippets.